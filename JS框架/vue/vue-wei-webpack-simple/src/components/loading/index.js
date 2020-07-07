import LoadingComponent from './loading.vue'
import LoadingComponent2 from './loading2.vue'

const Loading = {
    install : function (vue) {
        vue.component('Loading', LoadingComponent);
        vue.component('Loading2', LoadingComponent2);
    }
}

export default Loading;