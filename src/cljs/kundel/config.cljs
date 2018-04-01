(ns kundel.config
  (:require [re-frame.core :as rf]))

(def debug?
  ^boolean js/goog.DEBUG)

; Current device media format: either :mobile :tablet :computer :large :wide
(rf/reg-sub
  :env:current-device-media-format
  (fn env:responsive-breakpoint [db]
      (get-in db [:state :current-device-media-format])))
