import { PaginateSpring } from "./Misc";

export interface Book {
	id: string;
	indexNumber: string;
	studentName: string;
}
export type BooksResponse = PaginateSpring<Book>;

export interface AddBookCommand {
	authorName: string;
	title: string;
}
export interface EditBookCommand extends AddBookCommand {
	id: string;
}
