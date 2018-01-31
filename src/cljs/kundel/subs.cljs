(ns kundel.subs
  (:require [debux.cs.core :refer-macros [clog dbg break]]
            [re-frame.core :as rf]
            [re-frame.subs :as subs])
  (:require-macros [reagent.ratom :refer [reaction]]))

;; all users
(rf/reg-sub
  :who
  (fn who [db _]
    (get-in db [:data])))

; Current device media format: either :mobile :tablet :computer :large :wide
(rf/reg-sub
  :env:current-device-media-format
  (fn env:responsive-breakpoint [db]
      (get-in db [:state :current-device-media-format])))