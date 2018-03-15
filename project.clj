(defproject cypress "0.1.0-SNAPSHOT"

  :description "Writing Cypress end-to-end tests with ClojureScript."

  :url "https://github.com/eerohele/cypress-clojurescript-demo"

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.64"]

                 ;; Compojure, Ring, and Reagent are included only so that we
                 ;; can build a simple ClojureScript web application to test
                 ;; with Cypress.
                 [compojure "1.6.0"]
                 [ring/ring-defaults "0.3.1"]
                 [reagent "0.8.0-alpha2"]]

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-ring "0.12.3"]]

  :ring {:handler cypress.handler/app}

  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [org.slf4j/slf4j-nop "1.7.13" :scope "test"]
                                  ;; A ClojureScript wrapper for Mocha.
                                  ;;
                                  ;; Provides (describe ,,,), (it ,,,), etc.
                                  [mocha-latte "0.1.2"]
                                  ;; A ClojureScript wrapper for Chai.
                                  ;;
                                  ;; Provides (expect ,,,).
                                  [chai-latte "0.2.0"]]}}

  :cljsbuild {:builds
              ;; Typically, you'd use something like Figwheel for ClojureScript
              ;; development, but I wanted to keep things simple here.
              {:dev     {:source-paths ["src"]
                         :compiler     {:optimizations :none
                                        :main          cypress.app
                                        :output-to     "resources/public/js/app.js"
                                        :output-dir    "resources/public/js/dev"
                                        :asset-path    "/js/dev"
                                        :source-map    true}}

               :cypress {:source-paths ["test/cypress/specs"]
                         ;; If you want :optimizations :none for faster
                         ;; compilation, the only solution I could come up with
                         ;; was to serve the Google Closure assets from the
                         ;; application web server (that is, from http://localhost:3000/js/test).
                         ;;
                         ;; If you can live with :optimizations :simple, you
                         ;; can omit the weird :asset-path.
                         :compiler     {:optimizations :none
                                        :main          cypress.specs.main
                                        :output-to     "cypress/integration/main_spec.js"
                                        :output-dir    "resources/public/js/test"
                                        :asset-path    "http://localhost:3000/js/test"}}}})
