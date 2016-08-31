(ns lein-test.async-example.gomacro
  (:require [clojure.core.async :refer [go timeout]]))

(defn test-gomacro []
  (doseq [i (range 10)]
    (go
     (timeout (rand-int 1000))
     (println i)))
  (timeout 2000)
  nil)
