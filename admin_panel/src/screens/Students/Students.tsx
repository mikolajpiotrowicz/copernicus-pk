import * as React from "react";
import { Table, Button, Popconfirm, Modal, Form, Input, notification } from "antd";
import { ColumnProps } from "antd/es/table";
import { Student, StudentsResponse } from '../../models/Student';
import { createStudent, deleteStudent, getStudents } from '../../services/api/student';


export const Students: React.FC<{}> = () => {
	const [students, setStudents] = React.useState<StudentsResponse>();
	const [addStudentModalVisible, setStudentModalVisible] = React.useState(false);
	const [waiting, setWaiting] = React.useState(false);
	const [form] = Form.useForm();
	const columns = React.useMemo (
		(): ColumnProps<Student>[] => [
			{
				key: "id",
				title: "ID",
				dataIndex: "id",
			},
			{
				key: "studentIndex",
				title: "Index",
				dataIndex: "indexNumber",
			},
			{
				key: "studentName",
				title: "Name",
				dataIndex: "studentName",
			},
			{
				key: "password",
				title: "Password",
				dataIndex: "password",
			},
			{
				align: "right",
				title: "Action",
				key: "action",
				render: (record) => {
					return (
						<div className="dashboard__action-wrapper">
							<Popconfirm
								onConfirm={() => removeStudent(record.id)}
								title="Are you sure, that you want delete this student?"
							>
								<Button className="ml-15" type="danger">
									Delete
								</Button>
							</Popconfirm>
						</div>
					);
				}
			}
		
		],
		[students]
	);
	
	const fetchStudents = React.useCallback(async () => {
		const response = await getStudents();
		setStudents(response.data);
	}, [setStudents]);
	
	const removeStudent = React.useCallback(async (id: string) => {
		try {
			await deleteStudent(id);
			setStudents({...students, content: students.content.filter(student => student.id !== id)})
			notification.open({type: 'success', message: `Student deleted!`})
		} catch (e) {
			notification.open({
				message: `Filed when try to delete student with id: ${id}`, type: 'error'
			})
		}
		
	}, [students, setStudents]);
	
	
	const addNewStudent = React.useCallback(async (values) => {
		setWaiting(true);
		try {
			const response = await createStudent(values);
			setStudents({...students, content: [...students.content, response.data]});
			setStudentModalVisible(false);
			notification.open({type: 'success', message: `Student created!`})
		} catch (e) {
			if(e.response.status === 422) {
				notification.open({type: 'error', message: `Create student fail, student index already in use.`})
			} else {
				notification.open({type: 'error', message: `Create student fail, ${JSON.stringify(e)}`})
			}
		} finally {
			setWaiting(false)
		}
	}, [students]);
	
	
	const onFinish = async values => {
		return addNewStudent(values);
	};
	
	React.useEffect(() => {
		fetchStudents();
	},[])
	
	return (
		<div className='screen'>
			<div className='fw mb15'>
				<Button onClick={() => setStudentModalVisible(true)} type="primary"> + Add student</Button>
				<Modal confirmLoading={waiting} onCancel={() => setStudentModalVisible(false)}  onOk={form.submit} title="Add new student" visible={addStudentModalVisible}>
					<Form onFinish={onFinish} form={form}>
						<Form.Item rules={[{ required: true }]} label="Student name" name="studentName">
							<Input placeholder="Student name"  />
						</Form.Item>
						<Form.Item rules={[{ required: true }]} label="Student index" name="indexNumber">
							<Input placeholder="Student index" />
						</Form.Item>
					</Form>
				</Modal>
			</div>
		<Table
			rowKey="id"
			columns={columns}
			loading={!(!!students)}
			tableLayout="auto"
			dataSource={students && students.content}
			pagination={students && {
				defaultPageSize: students.size,
				total: students.totalElements,
				current: students.pageable.pageNumber + 1
			}}
		/>
		</div>
	);
};
