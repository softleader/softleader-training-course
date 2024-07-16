// dayjs
import dayjs from 'dayjs'
import utc from 'dayjs/plugin/utc'
import timezone from 'dayjs/plugin/timezone'
import weekOfYear from 'dayjs/plugin/weekOfYear'
import customParseFormat from 'dayjs/plugin/customParseFormat'
import objectSupport from 'dayjs/plugin/objectSupport'
import toObject from 'dayjs/plugin/toObject'
import zh_tw from 'dayjs/locale/zh-tw'

export default defineNuxtPlugin((_nuxtApp) => {
  dayjs.extend(utc)
  dayjs.extend(timezone)
  dayjs.extend(weekOfYear)
  dayjs.extend(customParseFormat)
  dayjs.extend(objectSupport)
  dayjs.extend(toObject)
  dayjs.locale(zh_tw)
  dayjs.tz.setDefault('Asia/Taipei')
  dayjs().format('YYYY-MM-DD HH:mm:ss')
})
