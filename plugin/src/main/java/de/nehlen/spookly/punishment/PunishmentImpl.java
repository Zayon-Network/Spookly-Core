package de.nehlen.spookly.punishment;

import de.nehlen.spookly.player.SpooklyOfflinePlayer;
import de.nehlen.spookly.punishments.Punishment;
import de.nehlen.spookly.punishments.PunishmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.UUID;

@Getter
public class PunishmentImpl implements Punishment {

    private final UUID uniqueId;
    private final UUID playerUniqueId;
    private final PunishmentType type;
    @Setter private Instant expiry;
    private final String reason;

    private final UUID creator;
    @Setter private UUID lastUpdater;
    private Instant creationTime;
    @Setter private Instant lastUpdate;

    protected PunishmentImpl(SpooklyOfflinePlayer target, SpooklyOfflinePlayer creator, PunishmentType type, Instant expiry, String reason) {
        this.uniqueId = UUID.randomUUID();
        this.playerUniqueId = target.uniqueId();
        this.type = type;
        this.expiry = expiry;
        this.reason = reason;

        this.creator = creator.uniqueId();
        this.lastUpdater = creator.uniqueId();
        this.creationTime = Instant.now();
        this.lastUpdate = Instant.now();
    }

    protected PunishmentImpl(UUID uniqueId, UUID playerUniqueId, PunishmentType type, Instant expiry, String reason, UUID creator, UUID lastUpdater, Instant creationTime, Instant lastUpdate) {
        this.uniqueId = uniqueId;
        this.playerUniqueId = playerUniqueId;
        this.type = type;
        this.expiry = expiry;
        this.reason = reason;
        this.creator = creator;
        this.lastUpdater = lastUpdater;
        this.creationTime = creationTime;
        this.lastUpdate = lastUpdate;
    }

    public static Punishment.Builder Builder(SpooklyOfflinePlayer target, SpooklyOfflinePlayer creator) {
        return new Builder(target, creator);
    }

    public static final class Builder implements Punishment.Builder {

        private final SpooklyOfflinePlayer target;
        private final SpooklyOfflinePlayer creator;

        private PunishmentType type;
        private Instant expiry;
        private String reason;

        public Builder(SpooklyOfflinePlayer target, SpooklyOfflinePlayer creator) {
            this.target = target;
            this.creator = creator;
        }

        @Override
        public Builder setType(PunishmentType type) {
            this.type = type;
            return this;
        }

        @Override
        public Builder setReason(String reason) {
            this.reason = reason;
            return this;
        }

        @Override
        public Builder setExpiry(Instant expiry) {
            this.expiry = expiry;
            return this;
        }

        @Override
        public Punishment build() {
            return new PunishmentImpl(target,creator,type,expiry,reason);
        }
    }
}
