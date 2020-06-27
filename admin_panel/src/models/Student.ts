import { PaginateSpring } from "./Misc";

export interface Student {
	id: string;
	indexNumber: string;
	studentName: string;
}
export type StudentsResponse = PaginateSpring<Student>;

export interface AddStudentCommand {
	indexNumber: string;
	studentName: string;
}
