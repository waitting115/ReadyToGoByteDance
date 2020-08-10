module.exports = {
    entry: './index.js',
    output: {
        filename: 'bundle.js'
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']//顺序不能反，从右往左读，先认识css在认识sytle
            },
            {
                test: /\.js$/,
                use: ['react-hot-loader','babel-loader'],//顺序不能反
                exclude: /node_modules/  //排除该文件夹
            }
        ]
    }
}