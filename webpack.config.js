const webpack = require('webpack');
const path = require('path');

const BUILD_DIR = path.resolve(__dirname, 'resources', 'public', 'js');
const APP_DIR = path.resolve(__dirname, 'resources', 'public', 'js', 'webpack');

const config = {
  entry: `${APP_DIR}/main.js`,
  output: {
    path: BUILD_DIR,
    filename: 'bundle.js'
  },
  module: {
    loaders: [
      {
        test: /\.css$/,
        loader: "style-loader!css-loader"
      },
      {
        test: /\.less$/,
        loader: "style-loader!css-loader!less-loader"
      },
      {
        test: /\.sass$/,
        loader: "style-loader!css-loader!sass-loader"
      },
      {
        test: /\.(png|woff|woff2|eot|ttf|svg|gif)/,
        loader: 'url-loader'
      }
    ]
   }
};

module.exports = config;
