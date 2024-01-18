package com.snippets.snippets.dtos;

import lombok.Data;

@Data
public class SnippetDto {


    private String Id;

    private String name;

    private String code;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
