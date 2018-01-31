(ns kundel.mock
  (:require [re-frame.core :as re-frame]
            [kundel.db :as db]))

(def test-db
  {:data "kundel"})

(re-frame/reg-event-db
  :mock-db
  (fn [_ _] test-db))
