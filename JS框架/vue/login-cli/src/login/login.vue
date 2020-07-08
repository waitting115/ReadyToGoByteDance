<template>
    <div class="box">
        <div class="container">
            <form action="http://localhost:5678/login" method="GET">
                <div class="form-group">
                    <label for="user">User:</label>
                    <input type="text" id="user" class="form-control" name="user" placeholder="user">
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" class="form-control" name="password" placeholder="password">
                </div>
                <br>
                <button type="submit" class="btn btn-success form-control">Login</button>
            </form>
        </div>
    </div>
</template>

<script>
// import 'bootstrap'
// import 'bootstrap/dist/css/bootstrap.min.css'
// import 'bootstrap/dist/js/bootstrap.min'
// import $ from 'jquery'
// import Vue from 'vue'
// console.log(Vue)
//  new Vue({
//      methods: {
//          login() {
//              consolee.log('1')
//          }
//      }
//  })


export default {
    
}
</script>

<style lang="stylus" scoped>
    .box {
        width: 50%;
        margin-left: 25%;
    }
</style>