import * as React from 'react';
import { Router, Switch } from 'react-router-dom';
import { createBrowserHistory } from "history";
import { RouteWithLayout } from "../../templates";
import { Students } from '../../screens/Students';
import { Books } from '../../screens/Books';



export const history = createBrowserHistory();

export const Routing = () => {
    return (
        <Router history={history}>
            <Switch>
                <RouteWithLayout exact path="/" component={Students} />
                <RouteWithLayout path="/students" component={Students} />
                <RouteWithLayout path="/books" component={Books} />
            </Switch>
        </Router>
    );
};
