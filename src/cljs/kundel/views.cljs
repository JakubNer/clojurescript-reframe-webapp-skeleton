(ns kundel.views
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [kundel.components.resize-notifier :as resize]
            [kundel.libs.media-size :as media-size]
            [debux.cs.core :refer-macros [clog dbg break]]))

(defn main-panel []
  (fn []
    (let [size @(rf/subscribe [:env:current-device-media-format])
          is-mobile? (media-size/is :mobile)]
       [:div
         [resize/render]
         [:> (aget js/window "deps" "sui" "Segment")
          [:div (str "hello " @(rf/subscribe [:who]) " from mobile? " is-mobile? " :: " size)]]])))
       