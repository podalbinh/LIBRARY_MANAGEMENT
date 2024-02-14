package com.librarymanagement.web.models;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDTO {
	private Long id;
	private String name;
	private String description;
}
