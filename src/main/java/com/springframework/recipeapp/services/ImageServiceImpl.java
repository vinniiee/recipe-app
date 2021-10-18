package com.springframework.recipeapp.services;

import com.springframework.recipeapp.domain.Recipe;
import com.springframework.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    RecipeRepository recipeRepository;

    @Transactional
    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {

        try{
            Recipe recipe = recipeRepository.findById(Long.valueOf(recipeId)).get();

            Byte[] byteObject = new Byte[file.getBytes().length];
            int i=0;
            for(byte b: file.getBytes()){
                byteObject[i++]=b;
            }

            recipe.setImg(byteObject);
            recipeRepository.save(recipe);




        }
        catch (IOException e){
                log.error("Error occured in IO operation");
                e.printStackTrace();
        }


    }
}
