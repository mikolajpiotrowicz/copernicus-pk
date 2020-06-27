import * as React from "react";
import { Layout } from "antd";
import { Route } from "react-router-dom";
import { SideMenu } from "../components/SideMenu";

export const RouteWithLayout = ({ component: Node, ...rest }) => {
  return (
    <Route
      {...rest}
      component={() => (
        <Layout style={{ minHeight: "100vh" }}>
          <SideMenu />
          <Layout>
            <Node />
          </Layout>
        </Layout>
      )}
    />
  );
};

export const RawLayout = ({ component: Node, ...rest }) => {
  return (
    <Route
      {...rest}
      component={() => (
        <Layout style={{ minHeight: "100vh" }}>
          <Layout>
            <Node />
          </Layout>
        </Layout>
      )}
    />
  );
};
