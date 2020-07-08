<template>
    <div class="box">
        <div class="container">
            <form action="http://localhost:5678/register" method="POST">
                <div class="form-group">
                    <label for="user">User</label>
                    <input type="text" id="user" class="form-control" name="user" placeholder='Please input your user'>
                </div>
                <div class="form-group">
                    <label for="password">Passowrd</label>
                    <input type="password" id="password" class="form-control" name="password" placeholder="Please input your password">
                </div>
                <div class="form-group">
                    <label for="pswagain">Passowrd again</label>
                    <input type="password" id="pswagain" class="form-control" name="pswagain" placeholder="Please input your password again">
                </div>
                <button type="submit" class="btn btn-success form-control">Register</button>
            </form>
        </div>
    </div>
</template>

<script>
export default {
    
}
</script>

<style lang="stylus" scoped>
    .box {
        width: 50%;
        margin-left: 25%;
    }
</style>