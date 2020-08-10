// import {smallNameA, b} from './a.js'
// console.log(smallNameA, b)

require('./index.css')

// import jsonMsg,{a, b, c} from './a'

import React from 'react'
import ReactDOM from 'react-dom'

import Wei2 from './ComponentDemo'

// let app = 'aaa';

// console.log(jsonMsg.a, jsonMsg.b);
// console.log(a, b, c);

class Wei extends React.Component{
    render() {
        return (
            <div>
                <h1>hello webpack-react!</h1>
                <Wei2/>
            </div>
        )
    }
}

ReactDOM.render(
    <Wei/>,
    document.getElementById('app')
)