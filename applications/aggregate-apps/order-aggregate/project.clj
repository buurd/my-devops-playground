(defproject order-aggregate "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.5"]
                 [ring-server "0.3.1"]
                 [liberator "0.10.0"]
                 [cheshire "5.2.0"]
                 [clj-http "1.1.2"]]
  :plugins [[lein-ring "0.8.12"]]
  :ring {:handler order-aggregate.handler/app
         :init order-aggregate.handler/init
         :destroy order-aggregate.handler/destroy}
  :profiles
  {:uberjar {:aot :all}
   :production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.3.1"]]}})
