(ns cypress.specs.main
  (:require-macros [latte.core :refer (before describe it)])
  (:require [latte.chai :refer (expect)])
  (:refer-clojure :exclude [first get]))


;; Save two entire characters!
(def cy js/cy)


(describe "Pokémon"
  (before []
    (.visit cy "http://localhost:3000"))

  (it "adds" []
    (.. cy (get "#name") (type "Pikachu"))
    (.. cy (get "#submit") (click))
    (.. cy
        (get "#pokémon")
        (first)
        ;; See https://docs.cypress.io/api/commands/should.html#Function
        (should (fn [pokémon]
                  (expect pokémon :to.have.length 1)
                  (expect pokémon :to.contain "Pikachu"))))))
