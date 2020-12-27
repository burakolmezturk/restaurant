import { NotificationManager } from 'react-notifications';
import 'react-notifications/lib/notifications.css';

export const createErrorNotification = (message) => {

    NotificationManager.error(message);

};
export const createSuccessNotification = (message) => {

    NotificationManager.success(message);

};
export const createWarningNotification = (message) => {

    NotificationManager.warning(message);

};
export const createInfoNotification = (message) => {

    NotificationManager.info(message);

};