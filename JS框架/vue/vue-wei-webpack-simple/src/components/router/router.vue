<template>
    <div>
        <p>routercomponent</p>
        <router-link to='/home'>home</router-link>
        <router-link to='/news'>news</router-link>

        <router-view></router-view>
    </div>
</template>


<script>
export default {
    
}
</script>

<style lang="stylus" scoped>

</style>