# Cypress & ClojureScript Demo

This is a small Clojure(Script) application that demonstrates one way to
write [Cypress] end-to-end tests in [ClojureScript].

See `test/cypress/specs/main.cljs` for the (very rudimentary) Cypress
test and read the comments in `project.clj`.

## Use

1. Clone this repo.
2. Install Yarn dependencies:

    ```bash
    yarn install
    ```

3. In one terminal, start the web server:

    ```bash
    lein ring server
    ```

4. In another terminal, start watching ClojureScript files for changes:

    ```bash
    lein cljsbuild auto dev cypress
    ```

5. In a third terminal, run Cypress:

    ```bash
    yarn run cypress open
    ```

6. In the Cypress GUI, Click on `main_spec.js`.

[ClojureScript]: https://www.clojurescript.org
[Cypress]: https://www.cypress.io
