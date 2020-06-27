import * as React from "react";
import { Table, Button, Popconfirm, Modal, Form, Input, notification } from "antd";
import { ColumnProps } from "antd/es/table";
import { AddBookCommand, Book, BooksResponse } from '../../models/Book';
import { createBook, deleteBook, getBooks, editBook } from '../../services/api/book';


export const Books: React.FC<{}> = () => {
	const [books, setBooks] = React.useState<BooksResponse>();
	const [editedBook, setEditedBook] = React.useState<Book>();
	const [addBookModalVisible, setBookModalVisible] = React.useState(false);
	const [editBookModalVisible, setEditBookModalVisible] = React.useState(false);
	const [waiting, setWaiting] = React.useState(false);
	const [form] = Form.useForm();
	const [editForm] = Form.useForm();
	const columns = React.useMemo (
		(): ColumnProps<Book>[] => [
			{
				key: "id",
				title: "ID",
				dataIndex: "id",
			},
			{
				key: "authorName",
				title: "Author",
				dataIndex: "authorName",
			},
			{
				key: "title",
				title: "Title",
				dataIndex: "title",
			},
			{
				key: "studentIndex",
				title: "Student Index",
				dataIndex: "studentIndex",
			},
			{
				align: "right",
				title: "Action",
				key: "action",
				render: (record) => {
					return (
						<>
							<Button onClick={() => handleStartEditing(record)}>
								Edit
							</Button>
							<Popconfirm
								onConfirm={() => removeBook(record.id)}
								title="Are you sure, that you want delete this student?"
							>
							
								<Button className='ml15' type="danger">
									Delete
								</Button>
							</Popconfirm>
					
							</>
					);
				}
			}
		
		],
		[books]
	);
	const handleStartEditing = React.useCallback((bookToEdit: Book) => {
		setEditedBook(bookToEdit);
		editForm.setFieldsValue(bookToEdit);
		setEditBookModalVisible(true);
	}, [editForm])
	
	const handleEdit = React.useCallback(async (formValues: AddBookCommand) => {
		setWaiting(true)
		try {
			const response = await editBook({...editedBook, ...formValues});
			setBooks({...books, content: books.content.map(book => {
				if(book.id === editedBook.id) {
					return response.data;
				}
				return book
			})})
			notification.open({type: 'success', message: `Book edited successfully.`})
		} catch (e) {
			notification.open({type: 'error', message: `Failed when edited book.`})
		} finally {
			 setWaiting(false);
			 setEditBookModalVisible(false);
		}
		
	}, [editedBook, editForm]);
	const fetchBooks = React.useCallback(async () => {
		const response = await getBooks();
		setBooks(response.data);
	}, [setBooks]);
	
	const removeBook = React.useCallback(async (id: string) => {
		try {
			await deleteBook(id);
			setBooks({...books, content: books.content.filter(book => book.id !== id)})
			notification.open({type: 'success', message: `Book deleted!`})
		} catch (e) {
			notification.open({
				message: `Filed when try to delete book with id: ${id}`, type: 'error'
			})
		}
		
	}, [books, setBooks]);
	
	
	const addNewBook = React.useCallback(async (values) => {
		setWaiting(true);
		try {
			const response = await createBook(values);
			setBooks({...books, content: [...books.content, response.data]});
			notification.open({type: 'success', message: `Book created!`})
		} catch (e) {
			if(e.response.status === 422) {
				notification.open({type: 'error', message: `Create book fail, student index already in use.`})
			} else {
				notification.open({type: 'error', message: `Create book fail, ${JSON.stringify(e)}`})
			}
		} finally {
			setWaiting(false)
			setBookModalVisible(false);
		}
	}, [books]);
	
	
	const onFinish = async values => {
		return addNewBook(values);
	};
	const onEditFinish = async values => {
		return handleEdit(values);
	};
	React.useEffect(() => {
		fetchBooks();
	},[])
	
	return (
		<div className='screen'>
			<div className='fw mb15'>
				<Button onClick={() => setBookModalVisible(true)} type="primary"> + Add book</Button>
				<Modal confirmLoading={waiting} onCancel={() => setBookModalVisible(false)}  onOk={form.submit} title="Add new book" visible={addBookModalVisible}>
					<Form labelCol={{span: 4, offset: 1}} onFinish={onFinish} form={form}>
						<Form.Item rules={[{ required: true }]} label="Author" name="authorName">
							<Input placeholder="Author"  />
						</Form.Item>
						<Form.Item rules={[{ required: true }]} label="Title" name="title">
							<Input placeholder="Title" />
						</Form.Item>
					</Form>
				</Modal>
				
				<Modal confirmLoading={waiting} onCancel={() => setEditBookModalVisible(false)}  onOk={editForm.submit} title="Edit book" visible={editBookModalVisible}>
					<Form labelCol={{span: 4, offset: 1}} onFinish={onEditFinish} form={editForm}>
						<Form.Item rules={[{ required: true }]} label="Author" name="authorName">
							<Input placeholder="Author"  />
						</Form.Item>
						<Form.Item rules={[{ required: true }]} label="Title" name="title">
							<Input placeholder="Title" />
						</Form.Item>
					</Form>
				</Modal>
				
				
				
			</div>
		<Table
			rowKey="id"
			columns={columns}
			loading={!(!!books)}
			tableLayout="auto"
			dataSource={books && books.content}
			pagination={books && {
				defaultPageSize: books.size,
				total: books.totalElements,
				current: books.pageable.pageNumber + 1
			}}
		/>
		</div>
	);
};
