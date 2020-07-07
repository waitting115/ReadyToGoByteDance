# Webpack Simple

Generates a webpack config with simple defaults and extendable options.

[![Build Status](https://travis-ci.com/helloitsjoe/webpack-simple.svg?branch=master)](https://travis-ci.com/helloitsjoe/webpack-simple)
[![Coverage Status](https://coveralls.io/repos/github/helloitsjoe/webpack-simple/badge.svg?branch=master)](https://coveralls.io/github/helloitsjoe/webpack-simple?branch=master)
[![npm](https://img.shields.io/npm/v/webpack-simple.svg)](https://www.npmjs.com/package/webpack-simple)

## Installation

Install as a dev dependency:

```
npm i -D webpack-simple
```

## Basic Usage

Webpack is great, but the configuration is verbose. Webpack 4 improved things
with a config-less setup, but as soon as you want something beyond the defaults
you need an entire config. This package aims to give you the best of both
worlds: a simple config, with the ability to configure just the parts you want.

```js
// webpack.config.js
const { makeWebpackConfig } = require('webpack-simple');
module.exports = makeWebpackConfig();
```

That's it!

This will generate a webpack config in `development` mode, with Babel/React and
css/sass loaders. It's the equivalent of:

```js
const config = {
  mode: 'development',
  target: 'web',
  module: {
    rules: [
      {
        test: /\.jsx?$/,
        exclude: [/.json$/, /node_modules/],
        use: [
          {
            loader: 'babel-loader',
            options: {
              presets: ['@babel/preset-env', '@babel/preset-react'],
              plugins: ['@babel/plugin-proposal-class-properties'],
            },
          },
        ],
      },
      {
        test: /\.s?css$/,
        use: [
          { loader: 'style-loader' },
          { loader: 'css-loader', options: { modules: true } },
          { loader: 'sass-loader' },
        ],
      },
    ],
  },
};
```

It's easy to add custom configuration:

```js
const productionConfig = makeWebpackConfig({ mode: 'production' });
const nodeTargeted = makeWebpackConfig({ target: 'node' });
const otherEntryAndOutput = makeWebpackConfig({
  entry: './index.js',
  output: {
    path: path.join(__dirname, 'public'),
    file: 'bundle.js',
  },
});
```

You can add or overwrite entire module rules:

```js
const { defaultWebpackRules } = require('webpack-simple');

const noBabelReactCSS = makeWebpackConfig({ rules: [] });
const includeOtherRules = makeWebpackConfig({
  rules: [...defaultWebpackRules, ...otherRules],
});
```

...or keep the above rules and just add/modify the default JS/CSS loaders:

```js
const { makeJS, makeCSS } = require('webpack-simple');

const use = [{ loader: 'other-loader', options: { foo: 'bar' } }];

const js = makeJS({ use });
const css = makeCSS({ use });

const customJSConfig = makeWebpackConfig({ js });
const customCSSConfig = makeWebpackConfig({ css });
```

By default, `makeJS` and `makeCSS` will return the JS/CSS rules in the config
above.

`makeJS` allows you to modify/override `use`, `exclude`, `babelPresets`, and
`babelPlugins`.

```js
const customJS = makeJS({ exclude: /testing/, babelPresets: 'some-preset' });

// customJS returns:
// {
//   test: /\.jsx?$/,
//   exclude: [/testing/],
//   use: [
//     {
//       loader: 'babel-loader',
//       options: {
//         presets: ['some-preset'],
//         plugins: ['@babel/plugin-proposal-class-properties'],
//       },
//     },
//   ],
// }
```

`makeCSS` allows you to modify/override `use`, `cssLoaderOptions`, and
`sassLoaderOptions`.

```js
const customCSS = makeCSS({ cssLoaderOptions: { foo: 'bar' } });

// customCSS returns:
// {
//   test: /\.s?css$/,
//   use: [
//     { loader: 'style-loader' },
//     { loader: 'css-loader', options: { foo: 'bar' } },
//     { loader: 'sass-loader' },
//   ],
// }
```

The full config input options with defaults:

```js
makeWebpackConfig({
  js, // Defaults to JS rule above
  css, // Defaults to CSS rule above
  rules, // Defaults to rules above
  target = 'web',
  mode = 'development',

  // All top-level webpack config options are available
  // as input options (all default to undefined):

  node,
  entry, // Falls back to Webpack's default: '/src/index.js'
  output, // Falls back to Webpack's default '/dist/main.js'
  serve,
  stats,
  watch,
  devtool,
  resolve,
  plugins,
  externals,
  devServer,
  performance,
  experiments,
  watchOptions,
  optimization,
});
```

The full list of module exports:

```js
module.exports = {
  makeWebpackConfig,
  makeJS,
  makeCSS,

  // Defaults provided so you can overwrite parts of them
  defaultWebpackRules,
  defaultBabelPlugins,
  defaultBabelPresets,
  defaultJSExclude,
  defaultJSUse,
  defaultCSSLoaderOptions,
  defaultCSSUse,
};
```
