(ns lein-test.stm-example.stm-demo)

(def ^:private n (ref 0))

(defn- f [x]
  (* x 2))

(defn exec [x]
  (dosync (ref-set n x)
          (alter n f)))
