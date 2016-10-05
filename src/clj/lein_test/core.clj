(ns lein-test.core
  (:require [lein-test.stm-example.nonstm-demo :as n]
            [lein-test.stm-example.stm-demo :as s])
  (:gen-class))

(defn -main []
  (println "Hello, World!")
  (println (n/exec 3))
  (println (s/exec 3)))
