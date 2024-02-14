package com.librarymanagement.web.models;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
	private Long id;

	private String isbn;
	private String name;

	private String serialName;
	private String description;
	private Set<Long> authors = new HashSet<Long>();
	private Set<Long> categories = new HashSet<Long>();
	private Set<Long> publishers = new HashSet<Long>();
}
