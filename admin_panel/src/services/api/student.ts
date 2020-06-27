import { axiosInstance } from '../client';
import { AxiosResponse} from 'axios';
import { AddStudentCommand, Student, StudentsResponse } from '../../models/Student';


export const getStudents = (): Promise<AxiosResponse<StudentsResponse>> => axiosInstance.get('/students');
export const createStudent = (command: AddStudentCommand): Promise<AxiosResponse<Student>> => axiosInstance.post('/students', command);
export const deleteStudent = (id: string):Promise<AxiosResponse<{}>> => axiosInstance.delete(`/students/${id}`);
