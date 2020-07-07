const defaultJSExclude = [/\.json$/, /node_modules/];
const defaultBabelPresets = ['@babel/preset-env', '@babel/preset-react'];
const defaultBabelPlugins = ['@babel/plugin-proposal-class-properties'];
const defaultJSUse = [
  {
    loader: 'babel-loader',
    options: {
      presets: defaultBabelPresets,
      plugins: defaultBabelPlugins,
    },
  },
];

const makeJS = ({
  exclude = defaultJSExclude,
  babelPresets,
  babelPlugins,
  use = defaultJSUse,
} = {}) => {
  if (!babelPresets && !babelPlugins) {
    return {
      test: /\.jsx?$/,
      exclude,
      use,
    };
  }
  const babelLoaderIndex = use.findIndex(use => use.loader === 'babel-loader');
  if (babelLoaderIndex === -1) {
    throw new Error('Babel options provided but no Babel loader found');
  }

  const babelLoader = use[babelLoaderIndex];
  const useCopy = [
    ...use.slice(0, babelLoaderIndex),
    {
      ...babelLoader,
      options: {
        ...babelLoader.options,
        presets: babelPresets || babelLoader.options.presets,
        plugins: babelPlugins || babelLoader.options.plugins,
      },
    },
    ...use.slice(babelLoaderIndex + 1),
  ];
  return {
    test: /\.jsx?$/,
    exclude,
    use: useCopy,
  };
};

// const makeTS = () => ({
//   test: /\.tsx?$/,
//   exclude: [/node_modules/],
//   use: [{ loader: 'ts-loader' }],
// });

const defaultCSSLoaderOptions = { modules: true };
const defaultCSSUse = [
  {
    loader: 'style-loader',
  },
  {
    loader: 'css-loader',
    options: defaultCSSLoaderOptions,
  },
  {
    loader: 'sass-loader',
  },
];

const makeCSS = ({ cssLoaderOptions, sassLoaderOptions, use = defaultCSSUse } = {}) => {
  if (!cssLoaderOptions && !sassLoaderOptions) {
    return { test: /\.s?css$/, use };
  }
  let customUse = [];
  if (cssLoaderOptions) {
    const cssLoaderIndex = use.findIndex(use => use.loader === 'css-loader');
    if (cssLoaderIndex === -1) {
      throw new Error('CSS loader options provided but no CSS loader found');
    }
    const defaultCssLoader = use[cssLoaderIndex];
    customUse = [
      ...use.slice(0, cssLoaderIndex),
      {
        ...defaultCssLoader,
        options: {
          ...defaultCssLoader.options,
          ...cssLoaderOptions,
        },
      },
      ...use.slice(cssLoaderIndex + 1),
    ];
  }
  if (sassLoaderOptions) {
    const sassLoaderIndex = use.findIndex(use => use.loader === 'sass-loader');
    if (sassLoaderIndex === -1) {
      throw new Error('SASS loader options provided but no SASS loader found');
    }
    const defaultSassLoader = use[sassLoaderIndex];
    customUse = [
      ...use.slice(0, sassLoaderIndex),
      {
        ...defaultSassLoader,
        options: {
          ...defaultSassLoader.options,
          ...sassLoaderOptions,
        },
      },
      ...use.slice(sassLoaderIndex + 1),
    ];
  }
  return {
    test: /\.s?css$/,
    use: customUse,
  };
};

const defaultWebpackRules = [makeJS(), makeCSS()];

const makeWebpackConfig = (options = {}) => {
  const {
    js,
    css,
    node,
    serve,
    stats,
    entry,
    watch,
    output,
    resolve,
    devtool,
    plugins,
    externals,
    devServer,
    performance,
    experiments,
    watchOptions,
    optimization,
    target = 'web',
    mode = 'development',
    rules = defaultWebpackRules,
    ...rest
  } = options;

  if (Object.keys(rest).length) {
    for (const key in rest) {
      console.warn(`${key} is unrecognized, but it will be added to the config.`);
    }
  }

  let customRules = rules;

  if (js) {
    customRules = rules.map(rule => (rule.test.test('.js') ? js : rule));
  }

  if (css) {
    customRules = rules.map(rule => (rule.test.test('.css') ? css : rule));
  }

  return {
    ...options,
    mode,
    target,
    module: {
      rules: customRules,
    },
  };
};

module.exports = {
  makeWebpackConfig,
  makeJS,
  makeCSS,
  defaultWebpackRules,
  defaultBabelPlugins,
  defaultBabelPresets,
  defaultCSSLoaderOptions,
  defaultCSSUse,
  defaultJSExclude,
  defaultJSUse,
};
