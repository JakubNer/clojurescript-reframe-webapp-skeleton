(ns kundel.events
    (:require [re-frame.core :as rf]
              [kundel.db :as db]
              [kundel.mock :as mock]
              [debux.cs.core :refer-macros [clog dbg break]]))

(rf/reg-event-db
  :ev:init
  (fn ev:init [_ _]
   ;db/default ;; TODO uncomment when ready to work with server
   mock/test-db))

(rf/reg-event-db
  :ev:f
  (fn ev:f [db [_ f]]
    (f db)))

(rf/reg-event-db
  :ev:update-current-device-media-format
  (fn ev:update-responsive-breakpoint [db [_ device-media-format]]
      (assoc-in db [:state :current-device-media-format] device-media-format)))
