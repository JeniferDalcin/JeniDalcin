(ns jeniferdalcin.views
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com :refer [at]]
   [jeniferdalcin.events :as events]
   [jeniferdalcin.routes :as routes]
   [jeniferdalcin.subs :as subs]))



;; Home Page

(defn home-title []
  (let [name (re-frame/subscribe [::subs/name])]
      [re-com/title
       :src   (at)
       :label (str @name)
       :level :level1])) ;; level 1 tem a ver com tamanho de fonte grau de importância

(defn link-to-about-page []
  [re-com/md-circle-icon-button
   :md-icon-name "zmdi-face"
   :src      (at)
   :style {:hover "#FEDC72"}
   :size :smaller
   :tooltip "about"
   :on-click #(re-frame/dispatch [::events/navigate :about])])

(defn link-to-daily-page []
  [re-com/md-circle-icon-button
   :md-icon-name    "zmdi-coffee"
   :src      (at)
   :size :smaller
   :tooltip "daily"
   :on-click #(re-frame/dispatch [::events/navigate :daily])])

(defn home-panel []
  [re-com/h-box
   :src      (at)
   :children 
   [[:div.flex
      [:img
       {:src "img/sunflower.png"
        :alt ""
        :style {:max-width "50vw"}}]]
    [:div.flex.center-block {:style {:align-self "center"}}
      [home-title]
      [link-to-about-page]
      [link-to-daily-page]]]])


(defmethod routes/panels :home-panel [] [home-panel])



;; About Page

(defn about-title []
  [re-com/title
   :src   (at)
   :label "This is the About Page."
   :level :level1])

(defn link-to-home-page []
  [re-com/md-circle-icon-button
   :md-icon-name    "zmdi-home"
   :src      (at)
   :style {:hover "background-color-red"}
   :size :smaller
   :tooltip "home"
   :on-click #(re-frame/dispatch [::events/navigate :home])])

(defn about-panel []
  [re-com/v-box
   :src      (at)
   :gap      "1em"
   :children [[about-title]
              [link-to-daily-page]
              [link-to-home-page]]])

(defmethod routes/panels :about-panel [] [about-panel])




;; Daily Page

(defn daily-title []
  [re-com/title
   :src   (at)
   :label "This is the Daily Page."
   :level :level1])


(defn daily-panel []
  [re-com/v-box
   :src      (at)
   :gap      "1em"
   :children [[daily-title]
              [link-to-about-page]
              [link-to-home-page]]])

(defmethod routes/panels :daily-panel [] [daily-panel])




;; main

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [re-com/v-box
     :src      (at)
     :height   "100%"
     :children [(routes/panels @active-panel)]]))
