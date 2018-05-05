(defproject lein-test "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[clj-time "0.14.3"]
                 [funcool/beicon "4.1.0"]
                 [funcool/cats "2.2.0"]
                 [org.clojure/algo.monads "0.1.6"]
                 [org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.238"]
                 [org.clojure/core.async "0.4.474"]
                 [org.clojure/core.match "0.3.0-alpha4"]
                 [org.clojure/test.check "0.9.0"]
                 [prismatic/schema "1.1.9"]]
  :plugins [[lein-cljsbuild "1.1.7"]]
  :source-paths ["src/clj"]
  :test-paths ["test/clj"]
  :cljsbuild
  {:builds [{:source-paths ["src/cljs"]
             :compiler {:output-to "resources/public/js/compiled/main.js"
                        :optimizations :whitespace
                        :pretty-print true}}]}
  :uberjar-name "lein-test.jar"
  :profiles
  {:uberjar {:aot :all
             :main lein-test.core}
   :dev {:dependencies [[org.clojure/tools.namespace "0.2.11"]
                        [org.clojure/test.check "0.9.0"]]
         :plugins [[jonase/eastwood "0.2.5"]
                   [lein-cljfmt "0.5.7"]
                   [lein-kibit "0.1.6"]]
         :source-paths ["env/dev/clj"]}})
