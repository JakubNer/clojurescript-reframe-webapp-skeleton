(defproject kundel "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [reagent "0.7.0" :exclusions [cljsjs/react cljsjs/react-dom]]
                 [re-frame "0.10.3-rc2"]
                 [philoskim/debux "0.4.1"]]

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-shell "0.5.0"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj" "src/cljs" "src/cljc"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :figwheel {:nrepl-port 7002
             :css-dirs ["resources/public/css"]}

  :profiles
  {:dev
   {:repl-options {:nrepl-middleware ["cemerick.piggieback/wrap-cljs-repl"]}
    :dependencies [[binaryage/devtools "0.9.9"]
                   [com.cemerick/piggieback "0.2.2"]
                   [figwheel-sidecar "0.5.15"]
                   [org.clojure/tools.nrepl "0.2.13"]]
    :plugins      [[lein-figwheel "0.5.15"]]}}

  :cljsbuild {:test-commands {; Test command for running the unit tests in "test-cljs" (see below).
                              ;     $ lein cljsbuild test
                              "unit" ["phantomjs"
                                      "phantom/unit-test.js"
                                      "resources/public/html/unit-test.html"]}

              :builds {:dev
                         {:source-paths ["src/cljs" "src/cljc" "src/test" "src/cljs/cljsjs"]
                          :figwheel     {:on-jsload "kundel.core/mount-root"}
                          :compiler     {:main                 kundel.core
                                         :output-to            "resources/public/js/compiled/app.js"
                                         :output-dir           "resources/public/js/compiled/out"
                                         :asset-path           "js/compiled/out"
                                         :optimizations        :none
                                         :source-map           true
                                         :source-map-timestamp true
                                         :preloads             [devtools.preload]
                                         :external-config      {:devtools/config {:features-to-install :all}}}}
                       :min
                         {:source-paths ["src/cljs" "src/cljc" "src/cljs/cljsjs"]
                          :compiler     {:main            kundel.core
                                         :output-to       "resources/public/js/compiled/app.js"
                                         :output-dir      "resources/public/js/compiled/min-out"
                                         :asset-path      "js/compiled/min-out"
                                         :optimizations   :advanced
                                         :externs         ["resources/externs.js"] ;; only for optimizations > :whitespace
                                         :closure-defines {goog.DEBUG false}
                                         :pseudo-names    false ;; true for debugging cryptic errors, comment out otherwise
                                         :pretty-print    false}} ;; true for debugging cryptic errors, false otherwise
                       :test
                         {:source-paths ["src/cljs" "src/cljc" "src/cljs/cljsjs" "src/test"]
                          :compiler     {:main            kundel.runner
                                         :output-to       "resources/public/js/compiled/test.js"
                                         :output-dir      "resources/public/js/compiled/test-out"
                                         :asset-path      "../js/compiled/test-out"
                                         :optimizations   :none}}}})
