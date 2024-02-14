INSERT INTO `authors` (`id`, `description`, `name`) VALUES
(1, 'Author A description', 'Author A'),
(2, 'Author B description', 'Author B'),
(3, 'Author C description', 'Author C'),
(4, 'Author D description', 'Author D'),
(5, 'Author E description', 'Author E'),
(6, 'Author F description', 'Author F'),
(7, 'Author G description', 'Author G'),
(8, 'Author H description', 'Author H'),
(9, 'Author I description', 'Author I'),
(10, 'Author J description', 'Author J');
INSERT INTO `books` (`id`, `description`, `isbn`, `name`, `serial_name`) VALUES
(1, 'Description for Book 1', 'ISBN123456789', 'Book 1', 'Serial 1'),
(2, 'Description for Book 2', 'ISBN234567890', 'Book 2', 'Serial 2'),
(3, 'Description for Book 3', 'ISBN345678901', 'Book 3', 'Serial 3'),
(4, 'Description for Book 4', 'ISBN456789012', 'Book 4', 'Serial 4'),
(5, 'Description for Book 5', 'ISBN567890123', 'Book 5', 'Serial 5'),
(6, 'Description for Book 6', 'ISBN678901234', 'Book 6', 'Serial 6'),
(7, 'Description for Book 7', 'ISBN789012345', 'Book 7', 'Serial 7'),
(8, 'Description for Book 8', 'ISBN890123456', 'Book 8', 'Serial 8'),
(9, 'Description for Book 9', 'ISBN901234567', 'Book 9', 'Serial 9'),
(10, 'Description for Book 10', 'ISBN012345678', 'Book 10', 'Serial 10');
INSERT INTO `publishers` (`id`, `name`) VALUES
(1, 'Publisher A'),
(2, 'Publisher B'),
(3, 'Publisher C'),
(4, 'Publisher D'),
(5, 'Publisher E'),
(6, 'Publisher F'),
(7, 'Publisher G'),
(8, 'Publisher H'),
(9, 'Publisher I'),
(10, 'Publisher J');
INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'Category A'),
(2, 'Category B'),
(3, 'Category C'),
(4, 'Category D'),
(5, 'Category E'),
(6, 'Category F'),
(7, 'Category G'),
(8, 'Category H'),
(9, 'Category I'),
(10, 'Category J');
INSERT INTO `books_publishers` (`book_id`, `publisher_id`) VALUES
(1, 1),
(1, 2),
(3, 1),
(4, 2),
(1, 9),
(3, 4),
(1, 7),
(2, 1),
(9, 9),
(10, 10);
INSERT INTO `books_categories` (`book_id`, `category_id`) VALUES
(1, 1),
(1, 2),
(2, 1),
(1, 3),
(2, 3),
(3, 1),
(3, 5),
(8, 3),
(1, 6),
(5, 4);
INSERT INTO `books_authors` (`author_id`, `book_id`) VALUES
(1, 1),
(1, 2),
(2, 1),
(1, 3),
(2, 3),
(3, 1),
(3, 5),
(3, 3),
(1, 6),
(5, 4);



