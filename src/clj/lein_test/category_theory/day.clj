(ns lein-test.category-theory.day
  (:require [clojure.spec.alpha :as s]))

(s/def ::day #{:sunday :monday :tuesday :wednesday :thursday :friday :saturday})
