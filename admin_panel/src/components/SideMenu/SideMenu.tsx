import * as React from 'react';
import { Layout, Menu, Typography } from 'antd'
import { Link } from 'react-router-dom';
import {
  UserOutlined,
  BarcodeOutlined
} from "@ant-design/icons";
const { Sider } = Layout;

export class SideMenu extends React.Component<{}, any>{
    public render() {
        return (
            <Sider className="sidebar">
                <Menu defaultSelectedKeys={[location.pathname]}>
                  <div className='logo'>Admin Panel</div>
                    <Menu.Item key='/students'><UserOutlined /><Link to="/students">Students</Link></Menu.Item>
                    <Menu.Item key='/books'><BarcodeOutlined /><Link to="/books">Books</Link></Menu.Item>
                </Menu>
            </Sider>
        )
    }
}
