(ns lein-test.async-example.gomacro
  (:require [clojure.core.async :refer [go]]))

(defn test-gomacro []
  (doseq [i (range 10)]
    (go
     (Thread/sleep (rand-int 1000))
     (println i)))
  (Thread/sleep 2000)
  nil)
