import login from './login.vue'

const Login = {
    install: function (vue) {
        vue.component('Login', login);
    }
}

export default Login;