package com.gmail.zagurskaya.service.model;

import com.gmail.zagurskaya.repository.model.ItemStatusEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemDTO {

    private Long id;
    @NotNull
    @Size(max = 40)
    private String name;
    @NotNull
    private ItemStatusEnum status;

    public ItemDTO() {
    }

    public ItemDTO(Long id, String name, ItemStatusEnum status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Long getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public ItemStatusEnum getStatus() {

        return status;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setStatus(ItemStatusEnum status) {

        this.status = status;
    }
}
