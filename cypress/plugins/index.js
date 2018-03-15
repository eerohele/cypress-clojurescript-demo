const watch = require('@cypress/watch-preprocessor')

module.exports = (on) => {
  on('file:preprocessor', watch())
}
