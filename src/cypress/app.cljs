(ns cypress.app
  (:require [reagent.core :as reagent]))


(defn input-val
  [event]
  (.. event -target -value))


(defrecord Pokémon
  [id name])


(defn <pokémon>
  [pokémon]
  [:ul#pokémon
   (for [{:keys [id name]} @pokémon]
     ^{:key id}
     [:li
      [:p name]])])


(defn <add-pokémon>
  [pokémon]
  (let [a-pokémon (reagent/atom (->Pokémon (random-uuid) nil))]
    [:div
     [:input {:id          :name
              :placeholder "Name"
              :type        :text
              :max-length  32
              :on-input    #(swap! a-pokémon assoc :name (input-val %))}]

     [:button {:id       :submit
               :type     :button
               :on-click #(swap! pokémon conj @a-pokémon)}
      "Add Pokémon"]]))


(defn <ui>
  []
  (let [pokémon (reagent/atom #{})]
    [:main
     [:h1 "ClojureScript Cypress Demo"]
     [<pokémon> pokémon]
     [<add-pokémon> pokémon]]))


(defn ^:export -main
  []
  (reagent/render [<ui>] (.getElementById js/document "#app")))


(-main)
