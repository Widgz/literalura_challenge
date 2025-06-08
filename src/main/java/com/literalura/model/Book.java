package com.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Book(String title, List<Author> authors, List<String> languages, Integer download_count) {}
