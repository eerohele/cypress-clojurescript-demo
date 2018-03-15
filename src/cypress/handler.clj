(ns cypress.handler
  (:require [clojure.java.io :as io]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))


(defroutes app-routes
  (GET "/" [] (io/resource "public/index.html"))
  (route/resources "/")
  (route/not-found "Not Found"))


(def app
  (wrap-defaults app-routes site-defaults))
