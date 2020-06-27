import { axiosInstance } from '../client';
import { AxiosResponse} from 'axios';
import { AddBookCommand, Book, BooksResponse, EditBookCommand } from '../../models/Book';


export const getBooks = (): Promise<AxiosResponse<BooksResponse>> => axiosInstance.get('/books');
export const createBook = (command: AddBookCommand): Promise<AxiosResponse<Book>> => axiosInstance.post('/books', command);
export const editBook = (command: EditBookCommand): Promise<AxiosResponse<Book>> => axiosInstance.put('/books', command);
export const deleteBook = (id: string):Promise<AxiosResponse<{}>> => axiosInstance.delete(`/books/${id}`);
