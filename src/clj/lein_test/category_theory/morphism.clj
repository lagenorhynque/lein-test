(ns lein-test.category-theory.morphism
  (:require [clojure.spec.alpha :as s]))

(s/def ::domain #{:d1 :d2 :d3})
(s/def ::codomain #{:ca :cb})

(s/def ::morphism (s/coll-of (s/coll-of (s/tuple ::domain ::codomain)
                                        :kind vector?
                                        :count 3)))
(def morphism
  (for [c1 [:ca :cb]
        c2 [:ca :cb]
        c3 [:ca :cb]]
    [[:d1 c1] [:d2 c2] [:d3 c3]]))
