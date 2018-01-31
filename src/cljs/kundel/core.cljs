(ns kundel.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [kundel.events :as events]
              [kundel.subs :as subs]
              [kundel.views :as views]
              [kundel.config :as config]
              [kundel.components.resize-notifier :as resize-notifier]))



(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn init-reactjs
  "Initialize ReactJS components pulled in: global initializations"
  [])

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:ev:init])
  (resize-notifier/dispatch-sync-new-size)
  (dev-setup)
  (init-reactjs)
  (mount-root))
