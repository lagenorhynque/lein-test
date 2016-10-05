(defproject lein-test "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [org.clojure/algo.monads "0.1.6"]
                 [org.clojure/core.async "0.2.391"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [org.clojure/test.check "0.9.0"]
                 [com.cemerick/pomegranate "0.3.1"]
                 [incanter "1.9.1"]
                 [korma "0.4.3"]
                 [hiccup "1.0.5"]
                 [mysql/mysql-connector-java "6.0.4"]]
 :plugins [[lein-cljsbuild "1.1.4"]]
 :source-paths ["src/clj" "test/clj"]
 :cljsbuild
 {:builds [{:source-paths ["src/cljs"]
            :compiler {:output-to "resources/public/main.js"
                       :optimizations :whitespace
                       :pretty-print true}}]}
 :uberjar-name "lein-test.jar"
 :profiles
 {:uberjar {:aot :all
            :main lein-test.core}})
