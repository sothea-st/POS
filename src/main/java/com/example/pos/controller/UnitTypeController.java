package com.example.pos.controller;
import java.util.ArrayList;

import com.example.pos.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import com.example.pos.components.JavaResponse;
import com.example.pos.entity.UnitType;
import com.example.pos.service.UnitTypesService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/unitType")
@Validated
public class UnitTypeController {
    @Autowired
    private UnitTypesService service;

    @PostMapping
    public ResponseEntity<?> addUnitType(@Valid @ModelAttribute UnitType u){
       UnitType data = service.addUnitType(u);
        return JavaResponse.success(data);
    }

    @GetMapping
    public ResponseEntity<?> getUnitType(){
        ArrayList<UnitType> data = service.getUnitType();
        return JavaResponse.success(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUnitTypeById(@PathVariable("id") int id){
        UnitType data = service.getUnitTypeById(id);
        return JavaResponse.success(data);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateUnitType(@PathVariable("id")int id,@Valid @RequestBody UnitType u){
        UnitType data = service.updateUnitType(id,u);
        return JavaResponse.success(data);
    }

//    @PostMapping("/{id}")
//	public ResponseEntity<?> updateUnitType(@PathVariable("id") int id,@RequestParam("nameKh")String nameKh, @RequestParam("nameEn")String nameEn) {
//
//		UnitType data = service.updateUnitType(id, nameKh , nameEn);
//		return JavaResponse.success(data);
//	}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUnityType(@PathVariable("id") int id ,@RequestBody UnitType u){
        service.deleteUnitType(id,u);
        return JavaResponse.deleteSuccess(id);
    }

}
