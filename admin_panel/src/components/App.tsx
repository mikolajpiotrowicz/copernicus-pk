import * as React from "react";
import { hot } from "react-hot-loader";
import "./../assets/scss/App.scss";
import {Routing} from "./Router";
import "antd/dist/antd.css";

class App extends React.Component<{}, undefined> {
    public render() {
        return (
            <Routing />
        );
    }
}

declare let module: object;

export default hot(module)(App);
