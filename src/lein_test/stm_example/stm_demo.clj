(ns lein-test.stm-example.stm-demo)

(defn- f [x]
  (ref (* x 2)))

(defn exec [x]
  @(f x))
